//functional programming approach using c/c++

#include <iostream>
#include <limits>

using namespace std;


float addition(float number_1, float number_2)
{
  float result;
  result=number_1 + number_2;
  return result;
}

float subtraction(float number_1, float number_2)
{
  float result;
  result=number_1 - number_2;   
  return result;
}
long int factorial(int n)
 {
  if (n<=1)
	return 1;
  else
	n=n*factorial(n-1);
	return n;
 }


float division(float dividend, float denominator)
{
	float result;

	if(denominator == 0)
	{
		cout<<"Division by 0!"<<endl;
		return numeric_limits<float>::max();
	}

	
	else
	{
		result = dividend/denominator;
		return result;
	}
}

float mult(float num_1,float num_2)
{
  float return_value;
  return_value = num_1*num_2;
  return return_value;
}

float power(float num_1, int num_2)
{
    float temp;
    if( num_2 == 0)
       return 1;
    temp = power(num_1, num_2/2);
    if (num_2%2 == 0)
        return temp*temp;
    else
    {
        if(num_2 > 0)
            return num_1*temp*temp;
        else
            return (temp*temp)/num_1;
    }
}

double square_root(double num){

    double dec=1;
    double result=0;
     
	while(dec>0.00001){
		if(result*result>num){
			result-=dec;
			dec/=10;
		}
		else if(result*result==num)
			return result;
		result+=dec;
	}  
  return result;
}

int main()
{
  float addition_,subtraction_,multiplication_,division_,power_;
  double sqroot;
  addition_=addition( 13.2, 321);
  subtraction_=subtraction(300.83 ,100.2);
  multiplication_=mult(19, 0);
  division_=division(182,31);
  power_=power(12,5);
  sqroot=square_root(3);
  factorialResult=factorial(5);
  cout<<"CMPE 352 GROUP 5"<<endl;
  cout<<"result of addition: "<<addition_<<endl;
  cout<<"result of subtraction: "<<subtraction_<<endl; 
  cout<<"result of division: "<<division_<<endl; 
  cout<<"result of multiplication: "<<multiplication_<<endl; 
  cout<<"result of power:"<<power_<<endl;
  cout<<"result of square_root:"<<sqroot<<endl;
  cout<<"result of factorial:"<<factorialRes<<endl;
  return 0;
}