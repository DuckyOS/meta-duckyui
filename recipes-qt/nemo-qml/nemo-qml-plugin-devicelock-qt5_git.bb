SUMMARY = "Device lock plugin for Nemo Mobile"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=cb8aedd3bced19bd8026d96a8b6876d7"

PV = "0.3.8+git${SRCPV}"
SRCREV = "0377d01763dc00b3ad4481a004840d97e74b5d10"

DEPENDS = "qtbase qtdeclarative glib-2.0 nemo-keepalive systemd mce-headers nemo-qml-plugin-dbus-qt5"
RDEPENDS:${PN} = "qtbase qtdeclarative nemo-qml-plugin-dbus-qt5"

SRC_URI = " \
    git://github.com/sailfishos/nemo-qml-plugin-devicelock.git;protocol=https;branch=master \
"

inherit qmake5 systemd pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

do_install:append() {
    mkdir ${D}/lib
    mv ${D}/usr/lib/systemd ${D}/lib/
}

FILES:${PN} += " \
    ${libdir}/libnemodevicelock.so.* \
    ${libdir}/qml/Nemo/DBus/* \
    ${libdir}/qml/org/nemomobile/devicelock/* \
    ${libexecdir}/nemo-devicelock \
    ${systemd_system_unitdir}/nemo-devicelock.socket \
    ${systemd_system_unitdir}/nemo-devicelock.service \
    ${sysconfdir}/dbus-1/system.d/org.nemomobile.devicelock.conf \
"

FILES:${PN}-dev += " \ 
    ${libdir}/libnemodevicelock.so \
    ${libdir}/pkgconfig/nemodevicelock.pc \
    ${libdir}/libnemodevicelock-host.a \
    ${includedir}/nemo-devicelock/*.h \
    ${includedir}/nemo-devicelock/private/*.h \
    ${includedir}/nemo-devicelock/host/*.h \
    ${datadir}/mkspecs/features/nemo-devicelock-host.prf \
"

SYSTEMD_SERVICE:${PN} = "nemo-devicelock.socket nemo-devicelock.service"