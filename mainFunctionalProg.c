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


float division(float dividend, float denominator)
{
	float result;

	if(denominator == 0)
	{
		cout<<"Division by 0!";
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



int main()
{
  float addition_,subtraction_,multiplication_,division_;
  addition_=addition( 13.2, 321);
  subtraction_=subtraction(300.83 ,100.2);
  multiplication_=mult(19, 0);
  division_=division(182,31);
  cout<<"CMPE 352 GROUP 5"<<endl;
  cout<<"result of addition: "<<addition_<<endl;
  cout<<"result of subtraction: "<<subtraction_<<endl; 
  cout<<"result of division: "<<division_<<endl; 
  cout<<"result of multiplication: "<<multiplication_<<endl; 
  return 0;
}