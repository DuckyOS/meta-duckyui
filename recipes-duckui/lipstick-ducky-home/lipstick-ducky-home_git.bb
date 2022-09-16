SUMMARY = "A mobile homescreen and shell for smartphones and tablets."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=65cab96178fd9b5838c7022b22133ce7"

PV = "0.0.1+git${SRCPV}"
SRCREV = "${AUTOREV}"

DEPENDS = "qtbase qtdeclarative lipstick-qt5 qtfeedback qttools qttools-native systemd bash-native"
DEPENDS:append:halium = " android-system"
RDEPENDS:${PN} = "bash sudo qtbase qtdeclarative qtquickcontrols2 qtgraphicaleffects-qmlplugins \
    lipstick-qt5 qtfeedback ngfd mce dsme ohm nemo-qml-plugin-configuration-qt5 ttf-lato \
    ducky-qml-plugin-feather-qt5 hicolor-icon-theme"

SRC_URI = " \
    git://github.com/DuckyOS/ducky-home-lipstick.git;protocol=https;branch=master \
    file://compositor_sargo.conf \
    file://duckyui.service \
    file://logind_ignorepowerkey.conf \
"
S = "${WORKDIR}/git"

inherit cmake_qt5 pkgconfig useradd systemd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-g 32011 ducky;"
USERADD_PARAM:${PN} = "-u 32011 -g 32011 -d /home/ducky -m"
USERADD_PARAM:${PN}:append = " -G video,input,sudo"
USERADD_PARAM:${PN}:append:halium = ",system,radio,bluetooth,graphics,camera,log,compass,wifi,adb,install,media,sdcard_rw,vpn,keystore,usb,drm,mdnsr,gps,media_rw,mtp,drmrpc,nfc,sdcard_r,clat,loop_radio,mediadrm,package_info,sdcard_pics,sdcard_av,sdcard_all,net_bt_admin,net_bt,inet,net_raw,net_admin,net_bw_stats,net_bw_acct,net_bt_stack"
USERADD_PARAM:${PN}:append = " ducky;"

do_install:append() {
    rm -r ${D}/${libdir}

    install -d ${D}/${systemd_system_unitdir}
    install -Dm 644 ${S}/../duckyui.service ${D}/${systemd_system_unitdir}/duckyui.service

    install -d ${D}${systemd_unitdir}/
    install -Dm 644 ${S}/../logind_ignorepowerkey.conf ${D}${systemd_unitdir}/logind.conf.d/10-${PN}.conf

    install -d ${D}/${localstatedir}/lib/environment/compositor
}

do_install:append:sargo() {
    install -Dm 755 ${S}/../compositor_sargo.conf ${D}/${localstatedir}/lib/environment/compositor/sargo.conf
}

FILES:${PN} += " \
    ${systemd_system_unitdir}/duckyui.service \
    ${systemd_unitdir}/logind.conf.d/10-${PN}.conf \
    ${datadir}/lipstick-ducky-home-qt5 \
    ${bindir}/lipstick \
"

SYSTEMD_SERVICE:${PN} = "duckyui.service"

FILES:${PN}:append:sargo = "${localstatedir}/lib/environment/compositor/sargo.conf"