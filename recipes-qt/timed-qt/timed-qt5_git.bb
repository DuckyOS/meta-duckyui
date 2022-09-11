SUMMARY = "Time daemon"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "3.6.15+git${SRCPV}"
SRCREV = "f845e4d83cce25c56d24ff1a387f138959dd7156"

DEPENDS = "libpcre qtbase libdsme systemd libiodata-qt5 libiodata-qt5-native libxslt sailfish-access-control"
RDEPENDS:${PN} = "systemd tzdata"

SRC_URI = " \
    git://github.com/sailfishos/timed.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig systemd

EXTRA_QMAKEVARS_CONFIGURE = "CONFIG+=dsme_dbus_if CONFIG+=ofono"

B = "${S}"

do_configure:prepend() {
    mkdir -p ${S}/src/h/timed-qt5
    ln -sf ../../lib/qmacro.h ${S}/src/h/timed-qt5
}

do_install:append() {
    sed -i 's/WantedBy=pre-user-session.target/WantedBy=default.target/'\
        "${D}/${systemd_user_unitdir}/timed-qt5.service"
    rm -r ${D}/opt
}

pkg_postinst_ontarget:${PN}:append() {
    #!/bin/sh -e
    systemctl enable --global timed-qt5.service
}

FILES:${PN} += " \
    ${sysconfdir}/dbus-1/system.d/%{name}.conf \
    ${sysconfdir}/timed-qt5.rc \
    ${bindir}/timed-qt5 \
    ${libdir}/libtimed-qt5.so.* \
    ${libdir}/libtimed-voland-qt5.so.* \
    ${libdir}/oneshot.d/setcaps-timed-qt5.sh \
    ${systemd_user_unitdir}/timed-qt5.service \
"

FILES:${PN}-dev += " \ 
    ${includedir}/timed-qt5/*.h \
    ${libdir}/libtimed-qt5.so \
    ${libdir}/libtimed-voland-qt5.so \
    ${libdir}/pkgconfig/timed-qt5.pc \
    ${libdir}/pkgconfig/timed-voland-qt5.pc \
    ${datadir}/mkspecs/features/timed-qt5.prf \
    ${datadir}/mkspecs/features/timed-voland-qt5.prf \
"
