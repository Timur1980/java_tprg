package ru.xtim.prts.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by timur.khisamutdinov on 12.06.2017.
 */
public class Groups extends ForwardingSet<GroupData> {


    private Set<GroupData> delegate;

    public Groups(Groups groups){
        this.delegate=new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate=new HashSet<GroupData>();
    }

    public Groups withAdded(GroupData group){
        Groups groups= new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups withOut(GroupData group){
        Groups groups=new Groups(this);
        groups.remove(group);
        return groups;
    }

    @Override
    protected Set delegate() {
        return delegate;
    }
}
