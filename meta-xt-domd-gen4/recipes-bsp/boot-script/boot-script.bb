SUMMARY = "Boot scripts for S4 boards: Spider, S4SK, and VC4"
DESCRIPTION = "Set of U-boot scripts that automate boot process on S4"

PV = "0.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit deploy

DEPENDS += "u-boot-mkimage-native"

SRC_URI:spider = "\
    file://boot-tftp-spider.txt \
    file://boot-emmc.txt \
"

SRC_URI:s4sk = "\
    file://boot-tftp-s4sk.txt \
    file://boot-emmc.txt \
"

SRC_URI:vc4 = "\
    file://boot-tftp-vc4.txt \
    file://boot-emmc.txt \
"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile() {
    uboot-mkimage -T script -d ${WORKDIR}/boot-tftp-${MACHINE}.txt ${B}/boot-tftp.uImage
    uboot-mkimage -T script -d ${WORKDIR}/boot-emmc.txt ${B}/boot-emmc.uImage
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${B}/boot-tftp.uImage ${DEPLOYDIR}/boot-tftp.uImage
    install -m 0644 ${B}/boot-emmc.uImage ${DEPLOYDIR}/boot-emmc.uImage
}

addtask deploy before do_build after do_compile
