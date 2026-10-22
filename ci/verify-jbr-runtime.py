#!/usr/bin/env python3
"""Verify that a packaged Compose Desktop app contains JBR 21."""

from __future__ import annotations

import argparse
import re
from pathlib import Path

VENDOR_MARKER = b"JetBrains s.r.o."


def find_one(root: Path, name: str) -> Path:
    matches = [path for path in root.rglob(name) if "runtime" in path.parts]
    if len(matches) != 1:
        raise SystemExit(
            f"Expected exactly one runtime {name!r} below {root}, found {len(matches)}: {matches}"
        )
    return matches[0]


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("package_root", type=Path)
    args = parser.parse_args()

    root = args.package_root.resolve()
    if not root.is_dir():
        raise SystemExit(f"Package root does not exist: {root}")

    release_file = find_one(root, "release")
    release = release_file.read_text(encoding="utf-8")
    if 'JAVA_VERSION="21.' not in release:
        raise SystemExit(f"Expected Java 21 in {release_file}, got:\n{release}")

    libraries = []
    for name in ("libjvm.so", "libjvm.dylib", "jvm.dll"):
        libraries.extend(path for path in root.rglob(name) if "runtime" in path.parts)
    if len(libraries) != 1:
        raise SystemExit(
            f"Expected exactly one packaged JVM library below {root}, found {len(libraries)}: {libraries}"
        )

    # A packaged Compose runtime image is jlink-trimmed and its `release` file carries no
    # vendor key at all, so the vendor assertion runs against the JVM binary itself. If the
    # release file does declare a vendor, it has to agree with the binary.
    jvm_library = libraries[0]
    if VENDOR_MARKER not in jvm_library.read_bytes():
        raise SystemExit(f"Packaged JVM is not a JetBrains Runtime: {jvm_library}")

    declared = re.search(r'^(?:JAVA_VENDOR|IMPLEMENTOR)="([^"]*)"', release, re.MULTILINE)
    if declared is not None and "JetBrains" not in declared.group(1):
        raise SystemExit(
            f"Release file vendor disagrees with the packaged JVM: {declared.group(1)} in {release_file}"
        )

    vendor = declared.group(1) if declared is not None else VENDOR_MARKER.decode()
    print(f"Verified JBR 21 in {root}")
    print(release.strip())
    print(f"JAVA_VENDOR={vendor} (verified in {jvm_library})")


if __name__ == "__main__":
    main()
