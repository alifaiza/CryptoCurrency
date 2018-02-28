import java.security.NoSuchAlgorithmException;


public class BlockChainDriver {
	public static void main(String args[]) throws NumberFormatException, NoSuchAlgorithmException{
		BlockChain transactions = new BlockChain(Integer.parseInt(args[0]));
		transactions.makeString();
		String argument = "";
		int amount = 0; 
		long nonce;
		
		while(!argument.equals("quit")){
			System.out.println("Command?");
			argument = args[0];
			if(argument.equals("mine")){
				System.out.println("Amount transferred?");
				amount = Integer.parseInt(args[0]);
				Block b = new Block(transactions.currentBlock, amount, transactions.getHash());
				nonce = b.getNonce();
				System.out.println("amount = " + amount + ", nonce = " + nonce);
			}
			else if(argument.equals("append")){
				System.out.println("Amount transferred?");
				amount = Integer.parseInt(args[0]);
				System.out.println("Nonce?");
				nonce = Integer.parseInt(args[0]);
				Block b = new Block(transactions.currentBlock, amount, transactions.getHash(), nonce);
				transactions.append(b);
			}
			else if(argument.equals("remove")){
				transactions.removeLast();
			}
			else if(argument.equals("check")){
				if(transactions.isValidBlockChain()){
					System.out.println("Chain is valid!");
				}
				else{
					System.out.println("Chain is not valid!");
				}
			}
			else if(argument.equals("report")){
				transactions.printBalances();
			}
			else if(argument.equals("help")){
				System.out.println("	mine: discovers the nonce for a given transaction");
				System.out.println("	append: appends a new block onto the end of the chain");
				System.out.println("	remove: removes the last block from the end of the chain");
				System.out.println("	check: checks that the block chain is valid");
				System.out.println("	report: reports the balances of Alice and Bob");
				System.out.println("	help: prints this list of commands");
				System.out.println("	quit: quits the program");
			}
			else if(argument.equals("quit")){ }
			else{
				System.out.println("That is not a valid command.");
			}
		}
	}
}
