package problem1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("Test Coins Set")
internal class CoinsTest {

    private val coins = listOf(1,2,5,10,20,50)
    private val lilliputCoins = listOf(1,4,15,20,50)

    @DisplayName("Given a set of British coins")
    @ParameterizedTest(name = "{index} => When target={0} then expect change of {1}")
    @MethodSource("britishTestCaseProvider")
    fun britishChange(target: Int, expected: List<Int>) {
        val change = coins.generateChange(target)
        assertEquals(change.size, expected.size)
        assertEquals(change.sorted(),expected.sorted())
    }

    @DisplayName("Given a set of Lilliputian coins")
    @ParameterizedTest(name = "{index} => When target={0} then expect change of {1}")
    @MethodSource("lilliputTestCaseProvider")
    fun lilliputChange(target: Int, expected: List<Int>) {
        val change = lilliputCoins.generateChange(target)
        assertEquals(change.size, expected.size)
        assertEquals(change.sorted(),expected.sorted())
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun britishTestCaseProvider(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(1, listOf(1)),
                    Arguments.of(2, listOf(2)),
                    Arguments.of(3, listOf(1,2)),
                    Arguments.of(4, listOf(2,2)),
                    Arguments.of(5, listOf(5)),
                    Arguments.of(9, listOf(2,2,5)),
                    Arguments.of(10, listOf(10)),
                    Arguments.of(49, listOf(2,2,5,20,20))
            )
        }

        @Suppress("unused")
        @JvmStatic
        fun lilliputTestCaseProvider(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(1, listOf(1)),
                    Arguments.of(2, listOf(1,1)),
                    Arguments.of(3, listOf(1,1,1)),
                    Arguments.of(4, listOf(4)),
                    Arguments.of(5, listOf(1,4)),
                    Arguments.of(9, listOf(1,4,4)),
                    Arguments.of(10, listOf(1,1,4,4)),
                    Arguments.of(23, listOf(15,4,4)),
                    Arguments.of(32, listOf(15,15,1,1))
            )
        }
    }

}