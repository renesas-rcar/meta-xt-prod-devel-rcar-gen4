FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Enable virtualization support
EXTRA_OEMAKE += "CFG_NS_VIRTUALIZATION=y \
                 CFG_VIRT_GUEST_COUNT=3 \
		 CFG_RCAR_MUTEX_DELAY=1 \
		 CFG_CORE_RESERVED_SHM=n \
                 CFG_REE_FS=y \
                 CFG_STANDALONE_FS=n \
"

