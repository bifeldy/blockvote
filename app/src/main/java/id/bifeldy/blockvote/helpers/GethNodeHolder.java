package id.bifeldy.blockvote.helpers;

import android.app.Activity;
import android.util.Log;

import org.ethereum.geth.Account;
import org.ethereum.geth.Geth;
import org.ethereum.geth.KeyStore;
import org.ethereum.geth.Node;
import org.ethereum.geth.NodeConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

import id.bifeldy.blockvote.R;

public class GethNodeHolder {

    private Node node;
    private Account account;

    private KeyStore keyStore;

    private static GethNodeHolder instance = null;

    private GethNodeHolder(Activity a, Node n) {
        this.node = n;
        this.keyStore = new KeyStore(a.getFilesDir() + "/keystore", Geth.LightScryptN, Geth.LightScryptP);
        try {
            if (this.keyStore.getAccounts().size() > 0) {
                this.account = this.keyStore.getAccounts().get(0);
                Log.i("[ETH_ACCOUNT] Path", this.account.getURL());
                Log.i("[ETH_ACCOUNT] Address", this.account.getAddress().getHex());
            }
        } catch (Exception e) {
            Log.e("[ETH_ERROR_ACCOUNT]", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
    }

    public static GethNodeHolder getInstance(Activity a, String userName) {
        if (instance == null) {
            try {
                Geth.setVerbosity(6);

                File gethDroidFolder = new File(a.getFilesDir() + "/GethDroid");
                if (!gethDroidFolder.exists()) gethDroidFolder.mkdirs();
                InputStream in = a.getResources().openRawResource(R.raw.static_nodes);
                FileOutputStream out = new FileOutputStream(gethDroidFolder + "/static-nodes.json");
                byte[] buff = new byte[1024];
                int read = 0;
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
                in.close();
                out.close();

                NodeConfig nc = new NodeConfig();
                nc.setEthereumNetworkID(4);
                nc.setEthereumNetStats(userName + "_Sony-XPeria-E6683:Respect my authoritah!@stats.rinkeby.io");
                nc.setEthereumGenesis(new Scanner(a.getResources().openRawResource(R.raw.genesis_block)).useDelimiter("\\A").next());

                Node n = Geth.newNode(a.getFilesDir().toString(), nc);
                Log.i("[ETH_NODE] eNode", n.getNodeInfo().getEnode());
                Log.i("[ETH_NODE] ID", n.getNodeInfo().getID());
                Log.i("[ETH_NODE] Name", n.getNodeInfo().getName());
                Log.i("[ETH_NODE] Protocol", String.valueOf(n.getNodeInfo().getProtocols()));

                instance = new GethNodeHolder(a, n);
            } catch (Exception e) {
                Log.e("[ETH_ERROR_CREATE]", Objects.requireNonNull(e.getMessage()));
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node n) {
        this.node = n;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account acc) {
        this.account = acc;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(KeyStore ks) {
        this.keyStore = ks;
    }

}
