# To Other Base

ამოცანა:
```
გადავიყვანოთ რიცხვი ნებისმიერ ბაზისში
```

## ბაზისები
ჩვენ ყოველდღიურ ცხოვრებაში ვიყენებთ 10-ობით სისტემას: 1, 3, 92, 127 და ა.შ. ამავდროულად გაგვიგია, რომ კომპიუტერები მუშაობენ ორობითი სისტემით: 0, 1, 101, 10010 და ა.შ. ეს ყველაფერი მხოლოდ და მხოლოდ არის რაოდენობების სხვადასხვანაირად ჩაწერის მეთოდები, მაგალითად, რიცხვი 10 10-ობითში არის 1010 2-ობითში

> ჩანაწერის სიმარტივისთვის: a(b) ჩანაწერში ვიგულისხმებ b-ობითში a-დ ჩაწერილ რიცხვს, მაგალითად 12(10) იქნება 10-ობითში ჩაწერილი რიცხვი, რომელიც გამოისახება 12-ით

ეს ეხება სხვა რიცხვებსაც: 11(10) = 1011(2), 147(10) = 10010011(2)
და სხვა ბაზისებში: 11(10) = 13(8), 11(10) = B(16)

> როდესაც ბაზისი მეტია 10-ზე, იშველიებენ სხვა სიმბოლოებს, ყველაზე ხშირად ასოებს, რომ 9-ზე დიდი ციფრების მაგივრობა გაწიონ

## როგორ ხდება ერთი ბაზისიდან მეორეში გადაყვანა?
მარტივი ალგორითმია, მაგრამ ჯერ ჯობია მაგალითით დაწყება

მაგალითი:   11(10) = ?(2)

ავიღოთ 11 და ვნახოთ მისი ნაშთი 2-ზე, რომელიც არის 1. ეს 1 დავიმახსოვროთ. შემდეგ 11 გავყოთ 2-ზე და ქვევით დავამრგვალოთ, რითიც მივიღებთ 5-ს. შემდეგ იგივეს ვიმეორებთ: 5-ის ნაშთი 2-ზე არის 1. დამახსოვრებულ პასუხს მარცხნიდან ვუწერთ ახლანდელ მიღებულ ნაშთს და ვიღებთ 11-ს, რომელსაც კვლავ ვიმახსოვრებთ. შემდეგ ვყოფთ 5-ს 2-ზე და დამრგვალებით ვიღებთ 2-ს. ასე იქამდე ვაგრძელებთ, სანამ გაყოფისა და დამრგვალების შედეგად 0-ს აღარ მივიღებთ, რის დროსაც ვწყვეტთ ოპერაციებს და დამახსოვრებულ რიცხვს ვაბრუნებთ. უფრო მარტივად ეს მაგალითი შემდეგნაირად მიმდინარეობს

```
    num     remainder   result
    11      1           1
    5       1           11
    2       0           011
    1       1           1011
```

`num` - რიცხვი, რომლის გადაყვანაც გვინდა სხვა ბაზისში 

`remainder` - ამ რიცხვის ნაშთი მოცემულ ბაზისში

`result` - დამახსოვრებული მნიშვნელობა

> თუ დავაკვირდებით, ყოველი შემდეგი გადასვლა ფაქტობრივად არის უფრო მცირე ზომის ამოცანის ამოხსნა, მაგალითად, 11(10)-ის ორობითში ჩასაწერად უნდა იცოდე 5(10)-ის ჩაწერა, ამის ჩასაწერად კიდევ 2(10)-ის და ა.შ. ყველაფერი ერთმანეთზე შენდება და ვიღებთ უფრო დიდი ამოცანის პასუხს, რაც რეკურსიის საფუძველია.

სიტყვიერად: 
1. იღებ გადასაყვანი რიცხვის მნიშვნელობას 
1. ნაშთავ ბაზისზე 
1. მაგ ნაშთის შესაბამის ციფრს წერ დასაბრუნებელი პასუხის მარცხნივ
1. გადასაყვან რიცხვს ყოფ ბაზისზე და ქვევით ამრგვალებ
1. ამ ოპერაციას იმეორებ მანამ, სანამ გადასაყვანი რიცხვი 0 არ გახდეა

## ალგორითმის გადაყვანა კოდში
თუ ალგორითმებს დაახლოებით ისეთი ფორმატით ჩამოაყალიბებთ, როგორც ზევით მიწერია სიტყვიერად, კოდში მისი გადაყვანა საკმაოდ მარტივი საქმეა
```java
	/*
	 * Pre-Condition : None
	 * 
	 * Post-Condition : Returns string of a given number in base notation
	 */
	public String toOtherBase(int num, int base){
		String res = "";
		while(num != 0){
			int residue = num % base;
			res = valueToDigit(residue) + res;
			num /= base;
		}
		return res;
	}
```
`res` ცვლადში ვინახავთ დასაბრუნებელ მნიშვნელობას, ხოლო არგუმენტებად ამ მეთოდს გადაეცემა თვითონ გადასაყვანი რიცხვი `num` და ის ბაზისი `base`, რომელშიც უნდა იქნას გადაყვანილი. while ციკლში ვაკეთებთ შემდეგს:

1.  `int residue = num % base;` - `residue` ცვლადში ვინახავთ მოცემული რიცხვის ბაზისზე ნაშთის მნიშვნელობას
1.  `res = valueToDigit(residue) + res;` - პასუხში მარცხნიდან ვუწერთ ციფრს, რომელიც მიესადაგება `residue` ცვლადის მნიშვნელობას
1.  `num /= base;` - მოცემულ რიცხვს ვყოფთ ბაზისზე და ქვევით ვამრგვალებთ(როდესაც Int-ებს ერთმანეთზე ვყოფთ, ისინი ავტომატურად მრგვალდებიან ქვევით)

ამ ოპერაციებს იქამდე ვასრულებთ, სანამ `num` ცვლადის მნიშვნელობა 0 არ გახდება, რის შემთხვევაშიც პროგრამა ჩერდება და აბრუნებს მნიშვნელობას

## დამატებით

### `valueToDigit` მეთოდი
ეს არის დამხმარე მეთოდი, რომელსაც რიცხვითი მნიშვნელობა გადაჰყავს შესაბამის ციფრში

### როცა ბაზისი უდრის 1-ს
ამ შემთხვევაში პროგრამა იციკლება, რადგან ყოველ ჯერზე რიცხვი იყოფა 1-ზე და ასეთი რიცხვი ვერასოდეს გახდება 0-ის ტოლი, თუ თავიდანვე 0 არ იყო