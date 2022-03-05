package com.example.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Yahya Foroud in 3/5/2022
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private final ArrayList<String> contacts = new ArrayList<>();
    private static final String TAG = "ContactsAdapter";
    private final ItemEventListener itemEventListener;

    public ContactsAdapter(ItemEventListener itemEventListener) {
        this.itemEventListener = itemEventListener;
        contacts.add("Ruthann Trustrie");
        contacts.add("Peadar Dawtrey");
        contacts.add("Yahya Foroud");
        contacts.add("Claude Crissil");
        contacts.add("Jacky Girardeau");
        contacts.add("Rubia Dominguez");
        contacts.add("Michaela Churchley");
        contacts.add("Harvey Pentelow");
        contacts.add("Neilla Langton");
        contacts.add("Marco Greaves");
        contacts.add("Liz Batchley");
        contacts.add("Lamond Littlepage");
        contacts.add("Malina Weir");
        contacts.add("Tomlin Lenchenko");
        contacts.add("Hy Pavelin");
        contacts.add("Jenelle Palin");
        contacts.add("Damon Knewstubb");
        contacts.add("Alex Ivanusyev");
        contacts.add("Hamil Callery");
        contacts.add("Karol Syer");
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: position => " + position);
        holder.bindContact(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private final TextView firstCharacterTv;
        private final TextView fullNameTv;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCharacterTv = itemView.findViewById(R.id.tv_contact_firstCharacter);
            fullNameTv = itemView.findViewById(R.id.tv_contact_fullname);
        }

        public void bindContact(String fullName) {
            fullNameTv.setText(fullName);
            firstCharacterTv.setText(fullName.substring(0, 1));
            //for update contact
            itemView.setOnClickListener(view -> itemEventListener.onItemClick(fullName, getAdapterPosition()));
            //for delete contact
            itemView.setOnLongClickListener(view -> {
                contacts.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                return false;
            });
        }

    }

    // Add new contact fun
    public void addNewContact(String fullName) {
        contacts.add(0, fullName);
        notifyItemInserted(0);
    }

    // updateContact
    public void updateContact(String fullName, int position) {
        contacts.set(position, fullName);
        notifyItemChanged(position);
    }

    public interface ItemEventListener {
        void onItemClick(String fullName, int position);
    }

}

