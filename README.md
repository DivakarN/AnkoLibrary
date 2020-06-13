# AnkoLibrary

Anko:
-----

A library that lets you simplify your interactions with the Android SDK, primarily by means of extension functions and properties.

Artifacts:
----------

1) SQLite
2) Coroutines
3) Commons
4) Layouts

Commons:
--------

1) Intents

startActivity<PlanetDetailActivity>(ARG_ITEM_ID to item.id)
intentFor<PlanetDetailActivity>(ARG_ITEM_ID to item.id)

2) Dialogs and toasts

snackbar(view, "My message")
toast("inside the action")						

3) Logging

warn { "Clicked on a planet" }

4) Dimensions and resources

Layouts:
--------

1) Android Views without XML
2) DSL syntax
3) Component reuse

class PlanetDetailUi : AnkoComponent<PlanetDetailFragment> {

    lateinit var planetDescription: TextView
    lateinit var planetComposition: TextView
    lateinit var planetMoons: TextView
    lateinit var planetOrbit: TextView

    override fun createView(ui: AnkoContext<PlanetDetailFragment>): View {
        return with(ui) {
            val container = verticalLayout {
                lparams(matchParent)

                planetDescription = textView {
                    setLineSpacing(8f, 1f)

                    onClick {
                        toast("Hey world from Anko!")
                    }
                }.lparams {
                    topMargin = dip(16)
                }

                planetComposition = textView {
                    setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_landscape, 0, 0, 0)

                    onLongClick {
                        owner.goToSpaceWebsite()
                    }
                }

                planetMoons = textView {
                    setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_brightness, 0, 0, 0)
                }

                planetOrbit = textView {
                    setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_loop, 0, 0, 0)
                }
            }

            container.applyRecursively { view ->
                when(view) {
                    is TextView -> {
                        with(view) {
                            padding = dip(16)
                            compoundDrawablePadding = dip(16)
                            setTextIsSelectable(true)
                            layoutParams.width = matchParent
                            textAppearance = R.style.TextAppearance_AppCompat_Medium
                        }
                    }
                }

            }
        }
    }

}

Coroutines:
-----------

@Test
fun usingAsync() {
    println("Starting...")

    GlobalScope.async(Dispatchers.IO) {
        delay(1_000L)
        println("Inside...")
    }

    Thread.sleep(1_500L)

    println("Stopping...")
}

@Test
fun usingAsyncWithDeferred() {
    println("Starting...")

    GlobalScope.launch(Dispatchers.IO) {
        val result: Deferred<Int> = async(Dispatchers.IO) {
            delay(1_000L)
            println("Inside...")
            42
        }

        println("the result: ${result.await()}")
    }

    Thread.sleep(1_500L)

    println("Stopping...")
}

@Test
fun usingAsyncTwice() {
    println("Starting...")

    GlobalScope.launch(Dispatchers.IO) {
        val result: Deferred<Int> = async(Dispatchers.IO) {
            delay(1_000L)
            println("Inside first...")
            42
        }

        val result2: Deferred<Int> = async(Dispatchers.IO) {
            delay(1_000L)
            println("Inside second...")
            8
        }

        println("the sum: ${result.await() + result2.await()}")
    }

    Thread.sleep(1_500L)

    println("Stopping...")
}

@Test
fun startWithLaunchNoSleep() {
    println("Starting...")

    GlobalScope.launch(Dispatchers.IO) {
        delay(1_000L)
        println("Inside...")
    }

    println("Stopping...")
}

@Test
fun startWithLaunchSleep() {
    println("Starting...")

    GlobalScope.launch(Dispatchers.IO) {
        delay(1_000L)
        println("Inside...")
    }

    Thread.sleep(1_500L)

    println("Stopping...")
}

@Test
fun startWithLaunchMultiple() {
    println("Starting...")

    GlobalScope.launch(Dispatchers.IO) {
        delay(1_000L)
        println("Inside 1...")
    }

    GlobalScope.launch(Dispatchers.IO) {
        delay(1_000L)
        println("Inside 2...")
    }

    Thread.sleep(1_500L)

    println("Stopping...")
}

@Test
fun startWithLaunchLazy() {
    println("Starting...")

    val job = GlobalScope.launch(Dispatchers.IO, CoroutineStart.LAZY) {
        delay(1_000L)
        println("Inside...")
    }

    job.start()

    Thread.sleep(1_500L)

    println("Stopping...")
}
