SUMMARY = "System settings plugin for Nemo Mobile"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.BSD;md5=caa037e0975ee5862b72644673e7590c"

PV = "0.8.1+git${SRCPV}"
SRCREV = "d26d206aec9e552c2407407eef72789259c6da54"

DEPENDS = "qtbase qttools-native qtdeclarative qtxmlpatterns timed-qt5 \
    profiled mce-headers mlite-qt5 libusb-moded-qt5 util-linux \
    libconnman-qt5 glib-2.0 sailfish-access-control-qt5 systemd \
    openssl libqofono nemo-qml-plugin-dbus-qt5"
RDEPENDS:${PN} = "connman mce libconnman-qt5 udisks2 mlite-qt5"

SRC_URI = " \
    git://github.com/sailfishos/nemo-qml-plugin-systemsettings.git;protocol=https;branch=master \
    file://0001-disable-libsailfishkeyprovider.patch \
    file://0002-disable-ssu.patch \
    file://0003-disable-devmode.patch \
"

inherit qmake5 pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

do_configure:prepend() {
    # Fix the call to qdbusxml2cpp.
    sed "s/\\\$\\\$\[QT_INSTALL_BINS\]\///g" -i "${S}/src/src.pro"

    # Fix the call to lrelease.
    sed "s/\\\$\\\$\[QT_INSTALL_BINS\]\///g" -i "${S}/translations/translations.pro"
}

do_install:append() {
    rm -r ${D}/opt
}

FILES:${PN} += " \
    ${libdir}/libsystemsettings.so.* \
    ${libdir}/qml/org/nemomobile/systemsettings/* \
    ${sysconfdir}/location/location.conf \
    ${datadir}/translations/*.qm \
    ${datadir}/translations/source/* \
"

FILES:${PN}-dev += " \ 
    ${libdir}/libsystemsettings.so \
    ${libdir}/pkgconfig/systemsettings.pc \
    ${includedir}/systemsettings/* \
"
