package xyz.volta

object Dependencies {

  object Web3j {
    const val core = "org.web3j:core:4.10.0"
    const val evm = "org.web3j:web3j-evm:4.10.0"
  }

  object Modules {
    const val sdk = ":module-core"
  }

  object Libs {
    const val jacksonCore = "com.fasterxml.jackson.core:jackson-core:2.14.2"
    const val jacksonData = "com.fasterxml.jackson.core:jackson-databind:2.14.2"
    const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.2"
  }

  object Testing {
    const val JUPITER = "org.junit.jupiter:junit-jupiter:5.4.2"
  }
}
