package com.MessageQueue.Framework.Producer;

import jakarta.annotation.Nullable;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;

public class Listener<K, V> implements ProducerListener<K, V> {

    @Override
    public void onSuccess(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata){
        System.out.println("printing Producer records............");
        System.out.println(producerRecord.toString());
        System.out.println("Printing record metadata.............");
        System.out.println(recordMetadata.toString());
    }

    @Override
    public void onError(ProducerRecord<K, V> producerRecord,@Nullable RecordMetadata recordMetadata, Exception exception) {
        LoggingProducerListener<K, V> loggingProducerListener = new LoggingProducerListener<>();
        loggingProducerListener.onError(producerRecord, recordMetadata, exception);
    }
}
