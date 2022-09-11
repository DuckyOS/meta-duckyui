SUMMARY = "Resource Policy Qt API"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.32.0+git${SRCPV}"
SRCREV = "43ec16ff923a9105bca76aa15a080fd7144d95fe"

DEPENDS = "qtbase libresource dbus"
RDEPENDS:${PN} = "qtbase libresource dbus"

SRC_URI = " \
    git://github.com/sailfishos/libresourceqt.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

B = "${S}"

do_install:append() {
    rm -rf "${D}/${libdir}/libresourceqt-qt5-tests"
    rm -rf "${D}/${datadir}/libresourceqt-qt5-tests"
}

FILES:${PN} += " \
    ${libdir}/libresourceqt5.so.* \
"

FILES:${PN}-dev += " \ 
    ${includedir}/resource/qt5/policy/*resource*.h \
    ${libdir}/libresourceqt5.so \
    ${libdir}/pkgconfig/libresourceqt5.pc \
"
