IMAGE_INSTALL += " \
    pciutils \
    devmem2 \
    optee-test \
"

IMAGE_INSTALL += "iproute2 iproute2-tc tcpdump nvme-cli"

IMAGE_INSTALL += " kernel-module-nvme-core kernel-module-nvme"

IMAGE_INSTALL += "e2fsprogs"
