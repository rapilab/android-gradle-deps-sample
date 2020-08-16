package com.phodal.gradoid.internal.scope

class VariantScopeImpl: VariantScope {
    val globalScope: GlobalScope

    constructor(globalScope: GlobalScope) {
        this.globalScope = globalScope
    }
}

interface VariantScope {

}
