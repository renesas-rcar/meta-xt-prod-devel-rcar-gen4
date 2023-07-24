FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:s4sk = " \
    file://early_printk_s4sk.cfg \
"

do_configure:append() {
    # merge configuration fragments manually using kconfig's native facilities
    ${S}/xen/tools/kconfig/merge_config.sh -m -O ${B}/xen ${B}/xen/.config ${WORKDIR}/*.cfg
}
