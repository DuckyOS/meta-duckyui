SUMMARY = "Feather toolkit for DuckyUI"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=65cab96178fd9b5838c7022b22133ce7"

PV = "0.0.1+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "qtbase qtdeclarative"
RDEPENDS:${PN} = "nemo-qml-plugin-configuration-qt5"

SRC_URI = " \
    git://github.com/DuckyOS/ducky-qml-plugin-feather.git;protocol=https;branch=master \
"

S = "${WORKDIR}/git"

do_install() {
    mkdir -p ${D}/${libdir}/qml/
    cp -r ${S}/Ducky ${D}/${libdir}/qml/
}

FILES:${PN} += " \
    ${libdir}/qml/Ducky/Feather/* \
"

