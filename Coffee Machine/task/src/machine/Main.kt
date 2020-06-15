package machine

import java.util.*
import kotlin.system.exitProcess

//val scanner = Scanner(System.`in`)

class CoffeeMachine {
    enum class STATE {
        MAIN_MENU,
        BUY_MENU,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_BEANS,
        FILLING_CUPS
    }
    enum class RECIPE(val type: String, val water: Int, val milk: Int, val beans: Int, val cups: Int, val money: Int) {
        ESPRESSO("espresso", 250, 0, 16, 1, -4),
        LATTE("latte", 350, 75, 20, 1, -7),
        CAPPUCCINO("cappuccino", 200, 100, 12, 1, -6);
    }
    internal class Inventory {
        private var water: Int = 400
        private var milk: Int = 540
        private var beans: Int = 120
        private var cups: Int = 9
        private var money: Int = 550

        fun buy(recipe: RECIPE) {
            this.water -= recipe.water
            this.milk -= recipe.milk
            this.beans -= recipe.beans
            this.cups -= recipe.cups
            this.money -= recipe.money
        }

        fun canMake(recipe: RECIPE): Boolean {
            return this.water >= recipe.water && this.milk >= recipe.milk && this.beans >= recipe.beans && this.cups >= recipe.cups
        }

        fun getMissingIngredient(recipe: RECIPE): String {
            var missing = ""
            when {
                this.water - recipe.water < 0 -> missing = "water"
                this.milk -  recipe.milk  < 0 -> missing = "milk"
                this.beans - recipe.beans < 0 -> missing = "beans"
                this.cups -  recipe.cups  < 0 -> missing = "cups"
            }
            return missing
        }

        fun takeMoney(): Int {
            val toTake = this.money
            this.money = 0
            return toTake
        }

        fun printInventory() {
            println("The coffee machine has:\n" +
                    "$water of water\n" +
                    "$milk of milk\n" +
                    "$beans of coffee beans\n" +
                    "$cups of disposable cups\n" +
                    "\$$money of money\n")
        }

        fun addWater(water: Int) {
            this.water += water
        }

        fun addMilk(milk: Int) {
            this.milk += milk
        }

        fun addBeans(beans: Int) {
            this.beans += beans
        }

        fun addCups(cups: Int) {
            this.cups += cups
        }
    }

    private val inventory: Inventory = Inventory()
    private var state: STATE = STATE.MAIN_MENU

    fun run(action: String) {
        when (state) {
            STATE.MAIN_MENU -> {
                when (action) {
                    "buy" -> {
                        state = STATE.BUY_MENU
                        printBuyMenu()
                    }
                    "fill" -> {
                        println("Write how many ml of water do you want to add: ")
                        state = STATE.FILLING_WATER
                    }
                    "take" -> { println("I gave you \$${inventory.takeMoney()}"); printMainMenu() }
                    "remaining" -> { inventory.printInventory(); printMainMenu() }
                    "exit" -> { exitProcess(0) }
                    else -> { printMainMenu() }
                }
            }
            STATE.BUY_MENU -> {
                when (action) {
                    "1" -> { handleBuy(RECIPE.ESPRESSO) }
                    "2" -> { handleBuy(RECIPE.LATTE) }
                    "3" -> { handleBuy(RECIPE.CAPPUCCINO) }
                    "back" -> { state = STATE.MAIN_MENU; printMainMenu() }
                    else -> { printBuyMenu() }
                }
            }

            STATE.FILLING_WATER -> {
                inventory.addWater(action.toInt())
                println("Write how many ml of milk do you want to add: ")
                state = STATE.FILLING_MILK
            }
            STATE.FILLING_MILK -> {
                inventory.addMilk(action.toInt())
                println("Write how many grams of coffee beans do you want to add: ")
                state = STATE.FILLING_BEANS
            }
            STATE.FILLING_BEANS -> {
                inventory.addBeans(action.toInt())
                println("Write how many disposable cups of coffee do you want to add: ")
                state = STATE.FILLING_CUPS
            }
            STATE.FILLING_CUPS -> {
                inventory.addCups(action.toInt())
                state = STATE.MAIN_MENU
            }
        }
    }

    private fun handleBuy(recipe: RECIPE) {
        if (inventory.canMake(recipe)) {
            inventory.buy(recipe)
            println("I have enough resources, making you a coffee!")
        } else {
            println("Sorry, not enough ${inventory.getMissingIngredient(recipe)}")
        }
        state = STATE.MAIN_MENU
        printMainMenu()
    }

    private fun printBuyMenu() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    }
    private fun printMainMenu() {
        println("Write action (buy, fill, take, remaining, exit): ")
    }

}

fun main() {
    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine()

    while (scanner.hasNext()) {
        coffeeMachine.run(scanner.next())
    }
}