package com.vkondrav.ram.collapsable.drawer.usecase

class HandleCollapsableDrawerUseCase(
    private val openCollapsableDrawerUseCase: OpenCollapsableDrawerUseCase,
    private val closeCollapsableDrawerUseCase: CloseCollapsableDrawerUseCase,
) {
    operator fun invoke(id: String, isOpen: Boolean) {
        when (isOpen) {
            true -> openCollapsableDrawerUseCase(id)
            false -> closeCollapsableDrawerUseCase(id)
        }
    }
}
