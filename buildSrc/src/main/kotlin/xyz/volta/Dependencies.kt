package xyz.volta

object Dependencies {

  object Modules {
    const val SDK = ":module-core"
  }

  object Web3j {
    const val CORE = "org.web3j:core:4.10.0"
    const val EVM = "org.web3j:web3j-evm:4.10.0"
  }

  object Jackson {
    const val CORE = "com.fasterxml.jackson.core:jackson-core:2.14.2"
    const val DATABIND = "com.fasterxml.jackson.core:jackson-databind:2.14.2"
  }

  object Testing {
    const val JUPITER = "org.junit.jupiter:junit-jupiter:5.4.2"
  }
}
