SUMMARY = "Configuration plugin for Nemo Mobile"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.BSD;md5=caa037e0975ee5862b72644673e7590c"

PV = "0.2.6+git${SRCPV}"
SRCREV = "f64d7327a3c781b50ef5fa5c4f328d2526309730"

DEPENDS = "qtbase qtdeclarative mlite-qt5"
RDEPENDS:${PN} = "qtbase qtdeclarative"

SRC_URI = " \
    git://github.com/sailfishos/nemo-qml-plugin-configuration.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

do_install:append() {
    # org.nemomobile.configuration legacy import
    mkdir -p "${D}"${libdir}/qml/org/nemomobile/configuration/
    ln -sf ${libdir}/qml/Nemo/Configuration/libnemoconfiguration.so "${D}"${libdir}/qml/org/nemomobile/configuration/
    sed 's/Nemo.Configuration/org.nemomobile.configuration/' < ${S}/src/qmldir > "${D}"${libdir}/qml/org/nemomobile/configuration/qmldir

    rm -r ${D}/opt
}

ERROR_QA:remove = " dev-so"

FILES:${PN} += " \
    ${libdir}/libnemoconfiguration.so \
    ${libdir}/qml/Nemo/Configuration/* \
    ${libdir}/qml/org/nemomobile/configuration/* \
"