package zuo.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a queue that has the following structure<br>
 * 1.call Method Add to add instance Cat or Dog to the queue<br>
 * 2.call Method pollAll to pop one instance in accordance with its enqueue order<br>
 * 3.call Method pollDog to pop one dog instance in accordance with its enqueue order<br>
 * 4.call Method pollCat to pop one cat instance in accordance with its enqueue order<br>
 * 5.call Method isEmpty to check if there are cat or dog instances in the queue<br>
 * 6.call Method isDogEmpty to check if there are dog instances in the queue<br>
 * 7.call Method isCatEmpty to check if there are cat instances in the queue<br>
 * 
 * 
 * If cat and dog are in separate queue, like Queue<Cat> and Queue<Dog>, problems occur when updating CatDogQueue<br>
 * Create a new class(PetQueue) for entering the queue. Field count acts as timestamp.
 * 
 * @author Alex
 * 
 */
public class CatDogQueue {

	private Queue<PetWrap> dogQueue;
	private Queue<PetWrap> catQueue;
	private long count;

	public CatDogQueue() {
		this.dogQueue = new LinkedList<PetWrap>();
		this.catQueue = new LinkedList<PetWrap>();
		this.count = 0;
	}

	public boolean add(Pet pet) {
		if (pet.getPetType().equals("dog")) {
			return dogQueue.offer(new PetWrap(pet, count++));
		} else if (pet.getPetType().equals("cat")) {
			return catQueue.offer(new PetWrap(pet, count++));
		} else {
			throw new RuntimeException("Not a dog or cat pet.");
		}
	}

	public Pet pollAll() {
		if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
			if (dogQueue.peek().getCount() < catQueue.peek().getCount()) {
				return dogQueue.poll().getPet();
			} else {
				return catQueue.poll().getPet();
			}
		} else if (!dogQueue.isEmpty()) {
			return dogQueue.poll().getPet();
		} else if (!catQueue.isEmpty()) {
			return catQueue.poll().getPet();
		} else {
			throw new RuntimeException("CatDogQueue is empty.");
		}
	}

	public Pet pollDog() {
		if (!dogQueue.isEmpty()) {
			return dogQueue.poll().getPet();
		} else {
			throw new RuntimeException("CatDogQueue is empty.");
		}
	}

	public Pet pollCat() {
		if (!catQueue.isEmpty()) {
			return catQueue.poll().getPet();
		} else {
			throw new RuntimeException("CatDogQueue is empty.");
		}
	}

	public boolean isEmpty() {
		return this.dogQueue.isEmpty() && this.catQueue.isEmpty();
	}

	public boolean isDogEmpty() {
		return this.dogQueue.isEmpty();
	}

	public boolean isCatEmpty() {
		return this.catQueue.isEmpty();
	}
	
	
	
	@Override
	public String toString() {
		return "CatDogQueue [dogQueue=" + dogQueue + ",\ncatQueue="
				+ catQueue + ",\nCatDogQueue count=" + count + "]";
	}

	public static void main(String[] args) {
		CatDogQueue queue = new CatDogQueue();
		Pet cat1 = new Pet("cat");
		Pet cat2 = new Pet("cat");
		Pet cat3 = new Pet("cat");
		Pet dog1 = new Pet("dog");
		Pet dog2 = new Pet("dog");
		Pet dog3 = new Pet("dog");
		
		queue.add(dog2);
		queue.add(cat1);
		queue.add(cat3);
		queue.add(dog1);
		queue.add(dog3);
		queue.add(cat2);
		System.out.println(queue);//count denotes the enqueue order
		
		queue.pollCat();
		System.out.println(queue + "\n");//count denotes the enqueue order
		queue.pollAll();
		System.out.println(queue + "\n");//count denotes the enqueue order
		queue.pollDog();
		System.out.println(queue + "\n");//count denotes the enqueue order
	}
}

/**
 * A class that extends class Pet with a timestamp-like field count
 * @author Alex
 *
 */
class PetWrap {
	private Pet pet;
	private long count;
	
	public PetWrap(Pet pet, long count) {
		this.pet = pet;
		this.count = count;
	}
	
	public Pet getPet() {
		return this.pet;
	}
	
	public long getCount() {
		return this.count;
	}
	
	public String getType(){
		return this.pet.getPetType();
	}

	@Override
	public String toString() {
		return "PetWrap [pet=" + pet + ", PetWarp count=" + count + "]";
	}
	
}



/**
 * **********************
 * Pet, Cat, Dog class
 * **********************
 * @author Alex
 *
 */
class Pet {
	private String type;
	
	public Pet(String type) {
		this.type = type;
	}
	public String getPetType() {
		return this.type;
	}
	@Override
	public String toString() {
		return "Pet [type=" + type + "]";
	}
}

class Dog extends Pet {
	public Dog(){
		super("dog");
	}
}

class Cat extends Pet {
	public Cat(){
		super("cat");
	}
}
