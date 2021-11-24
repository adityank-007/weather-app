package me.aditya.weather.database.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.aditya.weather.database.MongoDBFactoryConnection;
import me.aditya.weather.model.UserInfo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDAO {
    private final MongoCollection<Document> userCollection;

    public UserDAO(MongoDBFactoryConnection mongoDBFactoryConnection) {
        this.userCollection = mongoDBFactoryConnection.getClient()
                .getDatabase(mongoDBFactoryConnection.getMongoDBConnection().getDatabase())
                .getCollection("users");
    }
    public List<UserInfo>  getAllUsers()
    {
        final MongoCursor<Document> users = userCollection.find().iterator();
        List<UserInfo> result = new ArrayList<>();
        try
        {
            while(users.hasNext())
            {
                Document doc = users.next();
                result.add(mapUserFromDocument(doc));
            }
        }catch (Exception e)
        {
            log.error("Error while fetching users");

            //Do nothing
        }
        finally {
            users.close();
        }
        return result;
    }

    private UserInfo mapUserFromDocument(final Document doc)
    {
        return new UserInfo(doc.getString("userName")
                , doc.getString("password")
                , doc.getString("city")
        ,       doc.getList("roles",String.class));
    }

}
