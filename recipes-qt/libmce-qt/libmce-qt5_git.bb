SUMMARY = "A library of Qt bindings for mce"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://lib/src/qmceproxy.h;beginline=2;endline=35;md5=182cfa7b69494ca80b3539cc92dc96be"

PV = "1.4.2+git${SRCPV}"
SRCREV = "431168d7c91ab7c3cfc9d2283ab1a26f09422228"

DEPENDS = "qtbase qtdeclarative mce dbus"
RDEPENDS:${PN} = "qtbase qtdeclarative mce dbus"

SRC_URI = " \
    git://github.com/sailfishos/libmce-qt.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

FILES:${PN} += " \
    ${libdir}/libmce-qt.so.* \
    ${libdir}/qml/Nemo/Mce \
"

FILES:${PN}-dev += " \ 
    ${includedir}/mce-qt5/*.h \
    ${libdir}/libmce-qt.so \
    ${libdir}/pkgconfig/mce-qt5.pc \
"
