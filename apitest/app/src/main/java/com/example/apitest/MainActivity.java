package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonParsing(getJsonString());

        for(Recipe recipe : recipeList){
            Log.d("result name", recipe.getName());
            Log.d("result summary", recipe.getSummary());
            Log.d("result ingredient", recipe.getIngredient());
            Log.d("result calorie", recipe.getCalorie()+"");
            Log.d("result level", recipe.getLevel()+"");
        }

    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("BasicRecipe");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    private void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray recipeArray = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < recipeArray.length() ; i++)
            {
                JSONObject movieObject = recipeArray.getJSONObject(i);

                Recipe recipe = new Recipe();

                recipe.setName(movieObject.getString("RECIPE_NM_KO"));
                recipe.setIngredient(movieObject.getString("IRDNT_CODE"));
                recipe.setSummary(movieObject.getString("SUMRY"));

                recipeList.add(recipe);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}