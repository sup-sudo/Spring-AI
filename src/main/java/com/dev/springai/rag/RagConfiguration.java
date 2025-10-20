package com.dev.springai.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration

public class RagConfiguration {
    private static final Logger
    logger = LoggerFactory.getLogger(RagConfiguration.class);

    private final String vectorStore = "vectorstore.json";
    @Value("classpath:/data/models.json")
    private Resource models;

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = getVectorStoreFile();
        if (vectorStoreFile.exists()){
            logger.info("Vector Store already exists");
            simpleVectorStore.load(vectorStoreFile);
        }
        else{
            logger.info("Vector Store does not exist");
            TextReader textReader = new TextReader(models);
            textReader.getCustomMetadata().put("filename", "models.txt");
            List<Document> documents = textReader.get();
            TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
            List<Document> apply = tokenTextSplitter.apply(documents);
            simpleVectorStore.add(apply);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }

    private File getVectorStoreFile(){
        Path path = Paths.get("src","main","resources","data");
        String absolutePath = path.toFile().getAbsolutePath()+"/"+vectorStore;
        return new File(absolutePath);
    }

}
