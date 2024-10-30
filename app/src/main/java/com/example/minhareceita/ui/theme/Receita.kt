package com.example.minhareceita

class Receita(var nome: String) {
    private val ingredientes = mutableListOf<Ingrediente>()
    private var custoEmbalagens: Double = 0.0
    private var quantidadeUnidades: Int = 0

    fun adicionarIngrediente(ingrediente: Ingrediente) {
        ingredientes.add(ingrediente)
    }

    fun definirCustoEmbalagens(custo: Double) {
        custoEmbalagens = custo
    }

    fun definirQuantidadeUnidades(quantidade: Int) {
        quantidadeUnidades = quantidade
    }

    fun custoTotal(): Double {
        val custoIngredientes = ingredientes.sumOf { it.quantidade * it.preco }
        return custoIngredientes + custoEmbalagens
    }

    fun calcularPrecoVenda(percentualLucro: Double): Double {
        return custoTotal() * (1 + percentualLucro / 100)
    }

    fun calcularPrecoVendaPorUnidade(percentualLucro: Double): Double {
        return calcularPrecoVenda(percentualLucro) / quantidadeUnidades
    }

    fun limparIngredientes() {
        ingredientes.clear()
    }
}
