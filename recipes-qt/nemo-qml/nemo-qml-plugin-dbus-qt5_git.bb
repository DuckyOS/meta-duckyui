SUMMARY = "DBus plugin for Nemo Mobile"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://license.lgpl;md5=cb8aedd3bced19bd8026d96a8b6876d7"

PV = "2.1.29+git${SRCPV}"
SRCREV = "eaf1ab129a17d6c6a08496c24f8851e11031d0e8"

DEPENDS = "qtbase qtdeclarative dbus"
RDEPENDS:${PN} = "qtbase qtdeclarative"

SRC_URI = " \
    git://github.com/sailfishos/nemo-qml-plugin-dbus.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

do_install:append() {
    # org.nemomobile.dbus legacy import
    mkdir -p "${D}"${libdir}/qml/org/nemomobile/dbus/
    ln -sf ${libdir}/qml/Nemo/DBus/libnemodbus.so "${D}"${libdir}/qml/org/nemomobile/dbus/
    sed 's/Nemo.DBus/org.nemomobile.dbus/' < ${S}/src/plugin/qmldir > "${D}"${libdir}/qml/org/nemomobile/dbus/qmldir

    rm -r ${D}/opt
}

ERROR_QA:remove = " dev-so"

FILES:${PN} += " \
    ${libdir}/libnemodbus.so.* \
    ${libdir}/qml/Nemo/DBus/* \
    ${libdir}/qml/org/nemomobile/dbus/* \
"

FILES:${PN}-dev += " \ 
    ${libdir}/libnemodbus.so \
    ${libdir}/pkgconfig/nemodbus.pc \
    ${includedir}/nemo-dbus/*.h \
    ${includedir}/nemo-dbus/private/*.h \
"
