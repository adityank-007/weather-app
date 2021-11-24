package me.aditya.weather.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.aditya.weather.database.MongoDBConnection;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class AdvancedConfiguration extends Configuration {
    @NotNull
    private MongoDBConnection mongoDBConnection;

    @JsonProperty
    public MongoDBConnection getMongoDBConnection()
    {
        return mongoDBConnection;
    }
    @JsonProperty
    public void setMongoDBConnection(MongoDBConnection mongoDBConnection)
    {
        this.mongoDBConnection = mongoDBConnection;
    }
}
