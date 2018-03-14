package alignment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class AlignmentTest{

    @DisplayName("Perform Sequence Alignment")
    @ParameterizedTest(name = "{index} => Given seq1={0} & seq2={1} Then expect distance of {2} and alignments of {3} -> {4}")
    @MethodSource("alignmentTestCaseProvider")
    fun alignmentTest(x: String, y: String, expectedDistance: Int, expectedAlignment1: String, expectedAlignment2: String) {

        val (dist, alignment1, alignment2) = align(x, y)

        assertEquals(expectedDistance, dist)
        assertEquals(expectedAlignment1, alignment1)
        assertEquals(expectedAlignment2, alignment2)
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun alignmentTestCaseProvider(): Stream<Arguments> {
            return Stream.of( Arguments.of("smith", "smyth", 1, "smith", "smyth"),
                    Arguments.of("kitten", "sitting", 3, "kitten-", "sitting"),
                    Arguments.of("attcgccaggt", "aatcgcgatgg", 4, "attcgcca-ggt", "aatcgcgatgg-"),
                    Arguments.of("TGTATGGACAGGCCACGTCTTGATTATCCACTCATCTGCTCCTGGACTTCTGGGTTGCTTCTACCATTTGCCCCTGTTGGGAAGAGTGCTCTGTATTCATACGGGTTTTTGTGTGGACACACGTTTCAATTCTCTTGGGTACACAGGCGTGGATGTGCAGGAGTCAGGGAAAAAAGCTCACTGGTGCCACTGGGCATCAAGCCCAGCTTCCTAGGCTGCGAATGGGCAGGTCTGCGGCCAGGACTCGTCTTCTTTTCCACGTGGGGCCCCTAGCTTGGCACCTAGCACGTGGC",
                            "TGTACAGACAGGCCACTTTTTACCCACTCATCTGCTTCTGGACTTTTGGGTTGCTTCTACCATGTGGCCTGTTGGGAACAGTGCTCTGTTTGTATTCATGTACGGGTTTTTGTGTGGACACACATTTTCAAGTCTCTTGGGTACACAGGTGTAGAAGTGCCAGAGTTGGGGAAAAAGCTCACCTTTCTAGGCTGTGAATGGGCCCTGGCAAGTCTGTGGCCAGGACTCGTCTTCTCTTCCACATGGGGCCCCTAGCTTGGCACCTAGCACGTGGC",
                            62, "TGTATGGACAGGCCACGTCTTGATTATCCACTCATCTGCTCCTGGACTTCTGGGTTGCTTCTACCATTTGCCCCTGTTGGGAAGAGTGCTCTGT----ATTCAT--ACGGGTTTTTGTGTGGACACACGTTT-CAATTCTCTTGGGTACACAGGCGTGGATGTGC-AGGAGTCAGGGAAAAAAGCTCAC-TGGTGCCACTGGGCATCAAGCCCAGCTTCCTAGGCTGCGAATGGGCAGGTCTGCGGCCAGGACTCGTCTTCTTTTCCACGTGGGGCCCCTAGCTTGGCACCTAGCACGTGGC",
                            "TGTACAGACAGGCCAC-T-TT--TTACCCACTCATCTGCTTCTGGACTTTTGGGTTGCTTCTACCATGTGGCC-TGTTGGGAACAGTGCTCTGTTTGTATTCATGTACGGGTTTTTGTGTGGACACACATTTTCAAGTCTCTTGGGTACACAGGTGTAGAAGTGCCAG-AGTTGGGGAAAAA-GCTCACCTT-T-CTA--GG-C-TGT-GA--A--TG----GGCC-C---TGG-CAAGTCTGTGGCCAGGACTCGTCTTCTCTTCCACATGGGGCCCCTAGCTTGGCACCTAGCACGTGGC")
            )
        }
    }

}
