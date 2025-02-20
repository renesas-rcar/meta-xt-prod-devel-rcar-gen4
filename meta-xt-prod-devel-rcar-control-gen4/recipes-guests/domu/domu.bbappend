FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

RDEPENDS:${PN} = "backend-ready"
SRC_URI += "\
    file://domu-set-root \
    file://domu.service \
"
SRC_URI:remove += "file://domu-vdevices.cfg"

FILES:${PN} += " \
    ${libdir}/xen/bin/domu-set-root \
"

do_install:append() {
    # Install domu-set-root script
    install -d ${D}${libdir}/xen/bin
    install -m 0744 ${WORKDIR}/domu-set-root ${D}${libdir}/xen/bin

    # Call domu-set-root script
    echo "[Service]" >> ${D}${systemd_unitdir}/system/domu.service
    echo "ExecStartPre=${libdir}/xen/bin/domu-set-root" >> ${D}${systemd_unitdir}/system/domu.service
}
