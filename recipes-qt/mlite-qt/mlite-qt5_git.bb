SUMMARY = "Useful classes originating from MeeGo Touch"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

PV = "0.4.0+git${SRCPV}"
SRCREV = "e3e9ccac8a94fd4bef55915b0dc844001bc02ff7"

DEPENDS = "qtbase dconf glib-2.0 qttools-native"

SRC_URI = " \
    git://github.com/sailfishos/mlite.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

B = "${S}"

do_configure:prepend() {
    # Fix the call to qdbusxml2cpp.
    sed "s/\\\$\\\$\[QT_INSTALL_BINS\]\///g" -i "${S}/src/src.pro"

    # Fix the call to lrelease.
    sed "s/\\\$\\\$\[QT_INSTALL_BINS\]\///g" -i "${S}/tests/ut_mdesktopentry.pro"
}

do_install:append() {
    rm -r ${D}/opt
}

FILES:${PN} += " \
    ${libdir}/libmlite5.so.* \
    ${libexecdir}/mliteremoteaction \
"

FILES:${PN}-dev += " \ 
    ${includedir}/mlite5/* \
    ${libdir}/libmlite5.so \
    ${libdir}/pkgconfig/*.pc \
"
