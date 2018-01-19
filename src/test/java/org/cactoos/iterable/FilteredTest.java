/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.iterable;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link Filtered}.
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public final class FilteredTest {

    @Test
    public void filtersList() {
        MatcherAssert.assertThat(
            "Can't calculate the length of an iterable",
            new LengthOf(
                new Filtered<>(
                    // @checkstyle MagicNumber (1 line)
                    input -> input.length() > 4, new IterableOf<>(
                        "hello", "world", "друг"
                    )
                )
            ),
            new ScalarHasValue<>(2)
        );
    }

    @Test
    public void filtersEmptyList() {
        MatcherAssert.assertThat(
            "Can't calculate the length of an empty iterable",
            new LengthOf(
                new Filtered<>(
                    input -> input.length() > 1, new IterableOf<String>()
                )
            ),
            new ScalarHasValue<>(0)
        );
    }

    @Test
    public void filtersIterablesWithSize() {
        final Iterable<Integer> list = new Filtered<>(
            i -> i > 0,
            new IterableOf<>(1, 2, -1, 0, 1)
        );
        MatcherAssert.assertThat(
            "Can't filter the iterable twice",
            list, Matchers.iterableWithSize(3)
        );
        MatcherAssert.assertThat(
            "Can't filter the iterable twice, again",
            list, Matchers.iterableWithSize(3)
        );
    }

}
