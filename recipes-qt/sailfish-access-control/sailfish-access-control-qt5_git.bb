SUMMARY = "Sailfish Access Control library (Qt bindings)"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://../../LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de"

PV = "0.0.7+git${SRCPV}"
SRCREV = "5bc93590910c71245d5e2866ef0cbb00a3af2df6"

DEPENDS = "qtbase qtdeclarative glib-2.0 sailfish-access-control"
RDEPENDS:${PN} = "qtbase qtdeclarative glib-2.0 sailfish-access-control"

SRC_URI = " \
    git://github.com/sailfishos/sailfish-access-control.git;protocol=https;branch=master \
    file://LICENSE.LGPL \
"
S = "${WORKDIR}/git/qt"

inherit qmake5 pkgconfig

FILES:${PN} += " \
    ${libdir}/qml/Sailfish/AccessControl \
"
