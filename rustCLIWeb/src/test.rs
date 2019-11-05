use ::std::io;

fn add(a :i32,b : i32) -> i32{
    return a + b;
}

fn test() {
    println!("Nice Jon!");
    let mut x : i128 = 50000000000000000000000000000000000000 + 1;
    println!("{}",x);
    println!("{}",add(3,3));
    let y = '😻';
    println!("{}",y);
    let a = [1,2,3,4,5];
    println!("{}",a.len());

    for i in a.iter() {
        println!("{}",i);
    }

    //user input
    let mut input = String::new();

    io::stdin().read_line(&mut input).expect("Couldn't read line");

    println!("input data: {}",input);

    input = String::new();

    io::stdin().read_line(&mut input).expect("Couldn't read line");

    println!("input data: {}",input);


}