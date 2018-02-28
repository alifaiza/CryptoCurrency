import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
	private int num;
	private int amount;
	private Hash prevHash;
	private long nonce;
	private Hash hash;
	
	//Method constructor if nonce is not initially provided
	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException{
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = 0;
		this.hash = new Hash(calculateHash(this.num, this.amount, this.prevHash, this.nonce));
		while(!this.hash.isValid()){
			this.nonce++;
			this.hash = new Hash(calculateHash(this.num, this.amount, this.prevHash, this.nonce));
		}
	}
	//Method constructor if nonce is initially provider
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException{
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		this.hash = new Hash(calculateHash(this.num, this.amount, this.prevHash, this.nonce));
	}
	//Sets the previous hash to h
	public void setPrevHash(Hash h){
		this.prevHash = h;
	}
	//Returns num
	public int getNum(){
		return this.num;
	}
	//Returns amount
	public int getAmount(){
		return this.amount;
	}
	//Returns nonce
	public long getNonce(){
		return this.nonce;
	}
	//Returns the previous hash
	public Hash getPrevHash(){
		return this.prevHash;
	}
	//Returns the current hash
	public Hash getHash(){
		return this.hash;
	}
	//Returns a string saying the amount, the nonce, the previous hash, and the current hash of the current block
	public String toString(){
		return "Block (Amount: " + this.amount + ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")";
	}
	//Calculates a hash based on num, amount, a Hash, and a nonce
	public static byte[] calculateHash(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("sha-256");
		md.update((byte) num);	
		md.update((byte) amount);	
		md.update((byte) nonce);	
		if(prevHash != null){
			md.update(prevHash.getData());	
		}
	    byte[] hash = md.digest();
	    return hash;
	}	
}
