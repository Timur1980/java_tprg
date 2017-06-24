package ru.xtim.prts.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by timur.khisamutdinov on 12.06.2017.
 */
public class Contracts extends ForwardingSet<ContractData> {

    private Set<ContractData> delegate;

    public Contracts(Contracts contracts){
        this.delegate=new HashSet<ContractData>(contracts.delegate);
    }

    public Contracts() {
        this.delegate=new HashSet<ContractData>();
    }

    public Contracts(Collection<ContractData> contracts) {
        this.delegate=new HashSet<ContractData>(contracts);
    }

    public Contracts withAdded(ContractData contract){
        Contracts contracts= new Contracts(this);
        contracts.add(contract);
        return contracts;
    }

    public Contracts withOut(ContractData contract){
        Contracts contracts=new Contracts(this);
        contracts.remove(contract);
        return contracts;
    }

    @Override
    protected Set<ContractData> delegate() {
        return delegate;
    }
}
