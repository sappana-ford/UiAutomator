package ford

import common.CommonSuiteTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CommonSuiteTest::class,
    RecallFlowTest::class
)
class FordSuiteTest