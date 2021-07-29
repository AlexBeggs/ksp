package com.google.devtools.ksp.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated

class GetSymbolsFromAnnotationProcessor : AbstractTestProcessor() {
    val result = mutableListOf<String>()
    override fun toResult(): List<String> = result

    override fun process(resolver: Resolver): List<KSAnnotated> {
        result.add("==== Anno superficial====")
        resolver.getSymbolsWithAnnotation("Anno").forEach{
            result.addAnnotations(it)
            result.add(it.toString())
        }
        result.add("==== Anno in depth ====")
        resolver.getSymbolsWithAnnotation("Anno", true).forEach{ result.add(it.toString()) }
        result.add("==== Bnno superficial====")
        resolver.getSymbolsWithAnnotation("Bnno").forEach{
            result.addAnnotations(it)
            result.add(it.toString())
        }
        result.add("==== Bnno in depth ====")
        resolver.getSymbolsWithAnnotation("Bnno", true).forEach{ result.add(it.toString()) }
        return emptyList()
    }

    private fun MutableList<String>.addAnnotations(it: KSAnnotated) {
        it.annotations.forEach { annotation ->
            this.add(annotation.toString())
            annotation.arguments.forEach { argument -> this.add(argument.toString()) }
        }
    }

}
