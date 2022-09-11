package ir.sahab.sahabino.utility;

import com.google.gson.Gson;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class LogSerializer implements Serializer<Log>{
    @Override
    public byte[] serialize(String topic, Log data) {
        byte[] retVal = null;
        try {
            retVal = data.getJson().getBytes();
        } catch (Exception e) {
            throw new SerializationException("cannot Serialize this Log file" + data);
        }
        return retVal;
    }
}