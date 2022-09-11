DESCRIPTION = "This QPA plugin allows rendering on top of libhybris-based hwcomposer EGL \
platforms. The hwcomposer API is specific to a given Droid release, and \
sometimes also SoC type (generic, qcom, exynos4, ...)."
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://hwcomposer_backend.cpp;beginline=1;endline=40;md5=09c08382077db2dbc01b1b5536ec6665"

PV = "5.6.2.20+git${SRCPV}"
SRCREV = "f1d9aef9693bb5ed5f586f3e7c07ac6ee756e21f"

DEPENDS = "qtbase libhybris qtwayland virtual/android-headers"

# We need to be ${MACHINE_ARCH} as we need to compile the source against a specific
# Android version we select per machine
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Depends on libhybris which has this restriction
COMPATIBLE_MACHINE = "^halium$"

SRC_URI = " \
    git://github.com/mer-hybris/qt5-qpa-hwcomposer-plugin.git;branch=master;protocol=https \
"
S = "${WORKDIR}/git/hwcomposer"

inherit qmake5 pkgconfig

FILES:${PN} += "${OE_QMAKE_PATH_PLUGINS}/platforms/libhwcomposer.so"
FILES:${PN}-dev += "${libdir}/cmake"
