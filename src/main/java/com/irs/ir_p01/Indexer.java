package com.irs.ir_p01;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

/**
 *
 * @author fespinosa
 */
public class Indexer
{

    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException
    {

        Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
        EnglishAnalyzer analyzer = new EnglishAnalyzer(); // Uses PorterStemmer

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(indexDirectory, config);
    }

    public void close() throws CorruptIndexException, IOException
    {
        writer.close();
    }

    public void createIndex(Path file) throws IOException
    {

        Files.walkFileTree(file, new SimpleFileVisitor<Path>()
        {

            HTMLHandler hTMLHandler = new HTMLHandler();

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {

                if (!attrs.isDirectory())
                {
                    String fileName = file.getFileName().toString();

                    if (fileName.endsWith(".html") || fileName.endsWith(".htm"))
                    {
                        System.out.println(
                              "Indexing " + fileName);
                        Document document = hTMLHandler.getDocument(new FileInputStream(file.toFile()));
                        writer.addDocument(document);
                    }

                }

                return FileVisitResult.CONTINUE;
            }
        });

    }

}
