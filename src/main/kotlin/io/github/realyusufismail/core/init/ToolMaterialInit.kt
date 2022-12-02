/*
 * Copyright 2022 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.core.init

import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient

enum class ToolMaterialInit(
    private val harvestLevel: Int,
    private val maxUses: Int,
    private val efficiency: Float,
    private val attackDamage: Float,
    private val enchantValue: Int,
    private val repairMaterial: Ingredient
) : Tier {
    MJOLNIR(3, 1561, 8.0f, 3.0f, 10, Ingredient.EMPTY); // TODO: Add repair material

    override fun getUses(): Int {
        return maxUses
    }

    override fun getSpeed(): Float {
        return efficiency
    }

    override fun getAttackDamageBonus(): Float {
        return attackDamage
    }

    override fun getLevel(): Int {
        return harvestLevel
    }

    override fun getEnchantmentValue(): Int {
        return enchantValue
    }

    override fun getRepairIngredient(): Ingredient {
        return repairMaterial
    }
}
