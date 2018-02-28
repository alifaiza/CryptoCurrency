
public class Hash {
	private byte[] data;
	//constructor
	public Hash(byte[] data){
		this.data = data;
	}
	//Returns the hash
	public byte[] getData(){
		return this.data;
	}
	//Determines whether the hash is a valid hash
	public boolean isValid(){
		if(this.data[0] == 0 && this.data[1] == 0 && this.data[2] == 0){
			return true;
		}
		return false;
	}
	//Returns data as a String value
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < this.data.length; i++){
			str.append(String.format("%02X", Byte.toUnsignedInt(this.data[i])));
		}
		return str.toString();
	}
	//Returns true if the hash and other are structurally equal
	public boolean equals(Object other){
		if(other instanceof Hash){
			Hash x = (Hash) other;
			return (x.getData()).equals(this.data);
		}
		return false;
	}
	
}
