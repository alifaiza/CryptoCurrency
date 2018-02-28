import java.security.NoSuchAlgorithmException;


public class BlockChain{
	private Block firstBlock;
	private Block lastBlock;
	public int currentBlock = 0; 
	Node blocks;
	
	//Constructor for the function
	public BlockChain(int initial) throws NoSuchAlgorithmException{
		this.firstBlock = new Block(0, initial, null);
		this.lastBlock = this.firstBlock;
		this.blocks = new Node(this.firstBlock);
		currentBlock++;
	}
	
	//Creates a new block and adds it to the block chain
	public Block mine(int amount) throws NoSuchAlgorithmException{
		Block newBlock = new Block(currentBlock, amount, this.lastBlock.getHash());
		this.lastBlock = newBlock;
		insertNext(this.lastBlock, this.blocks);
		currentBlock++;
		return newBlock;
	}
	
	//Returns the size of the BlockChain
	public int getSize(){
		return currentBlock;
	}
	
	//Appends b to the block chain
	public void append (Block blk) throws IllegalArgumentException{
		blk.setPrevHash(this.lastBlock.getPrevHash());
		this.lastBlock = blk;
	}
	
	//Inserts a new node with block b 
	public void insertNext(Block b, Node n){
		if(n.next == null){
			n.next = new Node(b);
		}
		else{
			insertNext(b, n.next);
		}
	}
	
	//Removes the last element of the block chain
	public boolean removeLast(){
		if(currentBlock == 1){
			return false;
		}
		else{
			Node temp = this.blocks; 
			for(int i = 0; i < currentBlock - 1; i++){
				temp = temp.next;
			}
			temp.next = null;
			this.lastBlock = temp.b;
			currentBlock--;
			return true;
		}
	}
	//Returns the Hash of the last block in the chain
	public Hash getHash(){
		return this.lastBlock.getHash();
	}
	//Checks whether the block chain is valid
	public boolean isValidBlockChain(){
		Node temp = this.blocks;
		int aliceAmount = 0;
		int bobAmount = 0;
		for(int i = 0; i <= currentBlock; i++){
			if(!blocks.b.getHash().isValid()){
				return false;
			}
			if(i == 0){
				aliceAmount +=temp.b.getAmount();
			}
			else{
				aliceAmount += temp.b.getAmount();
				bobAmount -= temp.b.getAmount();
			}
			if(aliceAmount < 0 || bobAmount < 0){
				return false;
			}
			temp = temp.next;
		}
		return true;
	}
	//Prints out the balances of Alice and Bob
	public void printBalances(){
		int aliceAmount = 0;
		int bobAmount = 0;
		Node temp = this.blocks;
		for(int i = 0; i <= currentBlock; i++){
			if(i == 0){
				aliceAmount +=temp.b.getAmount();
			}
			else{
				aliceAmount += temp.b.getAmount();
				bobAmount -= temp.b.getAmount();
			}
			temp = temp.next;
		}
		System.out.println("Alice: " + aliceAmount + ", Bob: " + bobAmount);
	}
	//Prints out a string representation of each block in the block chain
	public void makeString(){
		Node temp = this.blocks;
		for(int i = 0; i <= currentBlock; i++){
			System.out.println(temp.b.toString());
			temp = temp.next;
		}

	}
}
