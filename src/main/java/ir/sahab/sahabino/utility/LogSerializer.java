package ir.sahab.sahabino;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class LogSerializer implements Serializer<Log>{
    @Override
    public byte[] serialize(String topic, Log data) {
        byte[] retVal = null;
        Gson gson = new Gson();
        try {
            retVal = gson.toJson(data).getBytes();
        } catch (Exception e) {
            throw new SerializationException("cannot Serialize this Log file" + data);
        }
        return retVal;
    }
}