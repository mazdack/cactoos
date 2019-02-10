/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2018 Yegor Bugayenko
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
package org.cactoos.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import org.cactoos.text.TextOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.InputHasContent;

/**
 * Test case for {@link WriterAsOutput}.
 *
 * @since 0.13
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class WriterAsOutputTest {
    /**
     * Temporary files and folders generator.
     */
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void writesLargeContentToFile() throws IOException {
        final Path temp = this.folder.newFile("cactoos-1.txt-1")
            .toPath();
        try (final OutputStreamWriter writer = new OutputStreamWriter(
            new FileOutputStream(temp.toFile()), StandardCharsets.UTF_8
        )) {
            new Assertion<>(
                "Can't copy Input to Output and return Input",
                () -> new TeeInput(
                    new ResourceOf("org/cactoos/large-text.txt"),
                    new WriterAsOutput(writer)
                ),
                new InputHasContent(
                    new TextOf(temp)
                )
            ).affirm();
        }
    }

}
