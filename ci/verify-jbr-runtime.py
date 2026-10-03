#!/usr/bin/env python3
"""Verify that a packaged Compose Desktop app contains JBR 21."""

from __future__ import annotations

import argparse
from pathlib import Path


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

    jvm_library = libraries[0]
    jvm_bytes = jvm_library.read_bytes()
    if b"JetBrains s.r.o." not in jvm_bytes:
        raise SystemExit(f"Packaged JVM is not a JetBrains Runtime: {jvm_library}")

    print(f"Verified JBR 21 in {root}")
    print(release.strip())
    print("JAVA_VENDOR=JetBrains s.r.o.")


if __name__ == "__main__":
    main()
