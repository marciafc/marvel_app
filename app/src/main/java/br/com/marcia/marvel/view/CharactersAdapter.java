package br.com.marcia.marvel.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.marcia.marvel.model.gson.Character;
import br.com.marcia.marvel.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterHolder> {

    private List<Character> mListCharacters;

    public CharactersAdapter(List<Character> characters) {
        this.mListCharacters = characters;
    }

    @Override
    public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_adapter_view, parent, false);
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterHolder holder, int position) {
        Character character = mListCharacters.get(position);

        holder.mName.setText(character.getName());

        boolean noHasDescription = character.getDescription().isEmpty();

        holder.mImage.setImageResource(noHasDescription ? R.drawable.ic_eye_opaque_24dp : R.drawable.ic_eye_black_24dp);
        final String description = character.getName() + "\n" +  (noHasDescription ? holder.itemView.getContext().getString(R.string.message_no_description_character) : character.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), description, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mListCharacters != null ? this.mListCharacters.size() : 0;
    }

    public void updateList(List<Character> characters) {
        int size = getItemCount();
        mListCharacters.addAll(characters);
        notifyItemRangeInserted(size, getItemCount());
    }

    public static class CharacterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.characters_text_name)
        TextView mName;

        @BindView(R.id.characters_image_eye)
        ImageView mImage;


        public CharacterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }



    }
}

