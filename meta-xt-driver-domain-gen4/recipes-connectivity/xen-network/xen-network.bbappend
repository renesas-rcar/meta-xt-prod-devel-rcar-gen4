FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://systemd-networkd-wait-online.conf \
"

RDEPENDS_${PN}_remove += "kernel-module-xt-nat"
RDEPENDS_${PN}_remove += "kernel-module-xt-tcpudp"
RDEPENDS_${PN}_remove += "kernel-module-xt-masquerade"
